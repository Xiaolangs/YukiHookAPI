/**
 * MIT License
 *
 * Copyright (C) 2022 HighCapable
 *
 * This file is part of YukiHookAPI.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is Created by fankes on 2022/1/29.
 */
@file:Suppress("SameParameterValue")

package com.highcapable.yukihookapi.demo

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Keep
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.highcapable.yukihookapi.hook.xposed.YukiHookModuleStatus

@Keep
class MainActivity : AppCompatActivity() {

    @Keep
    private var a = "没更改的变量"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // for test
        AlertDialog.Builder(this)
            .setTitle("Hook 方法返回值测试")
            .setMessage(test() + "\n变量：$a\n模块是否已激活：${YukiHookModuleStatus.isActive()}")
            .setPositiveButton("下一个") { _, _ ->
                AlertDialog.Builder(this)
                    .setTitle("Hook 方法参数测试")
                    .setMessage(test("这是没有更改的文字") + "\n${a(content = "这是原文")}\n模块是否已激活：${YukiHookModuleStatus.isActive()}")
                    .setPositiveButton("下一个") { _, _ ->
                        AlertDialog.Builder(this)
                            .setTitle("Hook 构造方法测试(stub)")
                            .setMessage(InjectTest("文字未更改").getString() + "\n模块是否已激活：${YukiHookModuleStatus.isActive()}")
                            .setPositiveButton("下一个") { _, _ ->
                                AlertDialog.Builder(this)
                                    .setTitle("Hook 构造方法测试(名称)")
                                    .setMessage(InjectTestName("文字没更改").getString() + "\n模块是否已激活：${YukiHookModuleStatus.isActive()}")
                                    .setPositiveButton("完成") { _, _ -> toast() }
                                    .show()
                            }.show()
                    }.show()
            }.show()
    }

    // for test
    @Keep
    private fun toast() = Toast.makeText(this, "我弹出来了，没有 Hook", Toast.LENGTH_SHORT).show()

    // for test
    @Keep
    private fun a(content1: String = "前缀", content: String) = "$content1${content}_后面加了一段文字"

    // for test
    @Keep
    private fun test() = "正常显示的一行文字"

    // for test
    @Keep
    private fun test(string: String) = string
}