package com.example.movie_data.util


fun String?.clearQuery(): String {
    if (this == null) {
        return ""
    }
    return this.trim()
}
