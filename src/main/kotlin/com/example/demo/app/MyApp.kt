package com.example.demo.app

import com.example.demo.view.MainView
import com.example.demo.view.MyStyles
import javafx.stage.Stage
import tornadofx.*

class MyApp: App(MainView::class,MyStyles::class){

    override fun start(stage: Stage) {

        with(stage) {
            minWidth = 600.0
            minHeight = 400.0

            super.start(this)
        }
    }

}
