package jmp0.appdbg.external

import javassist.ClassPool
import javassist.CtClass
import javassist.CtField
import javassist.NotFoundException

/**
 * @author jmp0 <jmp0@qq.com>
 * Create on 2022/5/17
 */
abstract class ClassCreatorBase(private val fullClassName: String,private val creatorType: CreatorType) {
    enum class CreatorType{
        PROJECT,
        RUNTIME
    }
    /**
     * @param fullClassName
     * @param type
     * 0 => get from this project below the Common.rootPackageName
     * 1 = > get from runtime
     */
    private fun getCtClass(fullClassName:String,type:CreatorType): CtClass =
        when(type){
            CreatorType.PROJECT->{
                ClassPool.getDefault().getCtClass("${Common.rootPackageName}.${Common.systemNowPackage}.${fullClassName}")
            }
            CreatorType.RUNTIME->{
                ClassPool.getDefault().getCtClass(fullClassName)
            }
        }
    companion object{
        fun getTargetClassName(packageName:String,className:String):String{
            val packageName = packageName.replace("${Common.rootPackageName}.${Common.systemNowPackage}.${Common.creatorName}.","")
            return "$packageName.$className"
        }
    }


    private fun checkModified(fullClassName: String): Boolean =
        try {
            getCtClass(fullClassName,CreatorType.RUNTIME).getDeclaredField(Common.modifiedSig)
            true
        }catch (e:NotFoundException){ false }

    protected abstract fun createImpl(ctClass: CtClass):CtClass;

    fun create(dstDir:String,force:Boolean){
        val modified = checkModified(fullClassName)
        if (!force and modified){
            println("$fullClassName has been modified,skip...")
            return
        }else println("generate $fullClassName class file")
        var ctClass = getCtClass(fullClassName,creatorType)
        if (!modified) ctClass.addField(CtField.make("public static boolean ${Common.modifiedSig} = true;",ctClass))
        ctClass = createImpl(ctClass);
        ctClass.also {
            it.replaceClassName(ctClass.name,fullClassName)
            it.writeFile(dstDir)
        }
    }
}