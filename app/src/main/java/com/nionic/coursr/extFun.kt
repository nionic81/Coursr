package com.nionic.coursr

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val URL_DRIVE = "https://drive.usercontent.google.com/"
const val BASE_URL =
    "https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download"
const val RUS_LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"

fun loadFragment(context: Context, fragment: Fragment, container: Int) {
    (context as FragmentActivity)
        .supportFragmentManager
        .beginTransaction()
        .replace(container, fragment)
        .commit()
}

@RequiresApi(Build.VERSION_CODES.O)
fun stringToLocalDate(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(dateString, formatter)
    val formatString = DateTimeFormatter.ofPattern("d MMMM yyyy")
    return localDate.format(formatString)
}

fun <T, R> Flow<List<T>>.mapList(transform: (T) -> R): Flow<List<R>> = map { list ->
    list.map { transform(it) }
}