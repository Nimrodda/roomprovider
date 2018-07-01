/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("unused")
package org.codepond.roomprovider.log

import java.util.*
import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.tools.Diagnostic.Kind.*

class RLog(private val messager : Messager) {
    private fun String.safeFormat(vararg args: Any): String {
        return try {
            format(args)
        } catch (ex: UnknownFormatConversionException) {
            // the input string might be from random source in which case we rather print the
            // msg as is instead of crashing while reporting an error.
            this
        }
    }

    fun d(element: Element, msg: String, vararg args: Any) {
        messager.printMessage(NOTE, msg.safeFormat(args), element)
    }

    fun d(msg: String, vararg args: Any) {
        messager.printMessage(NOTE, msg.safeFormat(args))
    }

    fun e(element: Element, msg: String, vararg args: Any) {
        messager.printMessage(ERROR, msg.safeFormat(args), element)
    }

    fun w(element: Element, msg: String, vararg args: Any) {
        messager.printMessage(WARNING, msg.safeFormat(args), element)
    }
}
