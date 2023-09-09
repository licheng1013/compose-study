package log

import java.io.OutputStreamWriter
import java.io.PrintWriter

val writer = PrintWriter(OutputStreamWriter(System.out, "UTF-8"), true)
fun myPrintln(str: String) {
    writer.println(str)
}