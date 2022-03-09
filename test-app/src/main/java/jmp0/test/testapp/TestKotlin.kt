package jmp0.test.testapp

import android.content.Context
import android.util.Base64
import android.util.Log

/**
 * @author jmp0 <jmp0@qq.com>
 * Create on 2022/3/8
 */
class TestKotlin() {

    private data class Base64Info(val data:String,val len:Int)

    private fun testxx(context: Context){
        testResources(context)
        testLopper()
    }

    private fun testBase64(): Base64Info {
        val xx by lazy { String(Base64.encode("testets".toByteArray(),0)) }
        return Base64Info(xx,xx.length)
    }

    //not implement
    public fun testResources(context: Context):String{
        return context.getResources().getString(R.string.app_name)
    }

    public fun testLopper(): Int {
        val _msgThread = MsgThread()
        _msgThread.start()
        Thread.sleep(100)
        _msgThread.sendMsg(0,"11111")
        Thread.sleep(1000)
        return 0
    }


    fun testString(context: Context) = apply {
        ImportTest().run {
            Log.d("test",context.packageName +"=>" + testBase64())

        }
    }
}