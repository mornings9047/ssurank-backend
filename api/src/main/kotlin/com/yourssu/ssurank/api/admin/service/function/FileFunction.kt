package com.yourssu.ssurank.api.admin.service.function

import java.io.File

class FileListFunction(){
    fun getFileList(): List<String> {
        val path = "Evaluate_Excel/"
        val dir = File(path)
        val fileList = dir.listFiles()
        val fileNames = arrayListOf<String>()

        for (file in fileList)
            if (file.isFile) fileNames.add(path.plus(file.name))

        return fileNames
    }
}