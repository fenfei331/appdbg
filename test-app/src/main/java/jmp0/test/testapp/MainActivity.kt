package jmp0.test.testapp

// modify fenfei
// import android.support.v7.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.MessageQueue
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import jmp0.test.testapp.net.TestNetWork

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FileTest().testAll()
//        TestContext(this).testAll()
//        TestAES()
//        TestNative().testAll()
    }
}