package com.example.demo.view

import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.util.Duration
import tornadofx.*
import java.util.*

class MainView : View("Drawer TornadoFX") {

    var leftDrawer : VBox by singleAssign();
    var rightDrawer : VBox by singleAssign();
    var centerNode : VBox by singleAssign();

    override val root: ScrollPane = scrollpane {
//        minWidth = 600.0
//        minHeight = 400.0
        maxWidth = 600.0
        maxHeight = 400.0

        hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        vbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
          hbox {
              leftDrawer = vbox {
                  minHeight = 380.0
                  minWidth = 40.0 //600.0
                  addClass(MyStyles.whiteBackground);
                  button("center view") {
                      alignment = Pos.CENTER
                      text = "Toggle left drawer"
                      action { toggleLeftDrawer() }
                  }
              }
              centerNode = vbox {
                  minWidth = 600.0
                  addClass(MyStyles.redBackground);
                  button("center view") {
                      alignment = Pos.CENTER
                      text = "Toggle left drawer"
                      action { toggleLeftDrawer() }

                  }
                  button("center view") {
                      alignment = Pos.CENTER
                      text = "Toggle right drawer"
                      action { toggleRightDrawer() }

                  }
              }
              rightDrawer = vbox {
                  minHeight = 400.0
                  minWidth = 40.0//600.0
                  addClass(MyStyles.whiteBackground);
                  button("center view") {
                      alignment = Pos.CENTER
                      text = "Toggle right drawer"
                      action { toggleRightDrawer() }

                  }
              }
          }
        hvalue = 0.48
    }

    var leftDrawerOpen:Boolean = false;
    var rightDrawerOpen:Boolean = false;

    override fun onDock() {
        super.onDock()
        Timer().schedule(
                object : TimerTask() {
                    override fun run() {
                        root.hvalue = 0.48
                    }
                },
                100
        )
    }

    fun toggleLeftDrawer(){

        if(leftDrawerOpen){
            println("Fechando drawer...")
            timeline {
                keyframe(Duration.seconds(.2)) {
                    keyvalue(root.hvalueProperty(),0.48)
                }
            }
            println("Fechado!")
        }else{
            println("Abrindo drawer...")
            timeline {
                keyframe(Duration.seconds(.2)) {
                    keyvalue(root.hvalueProperty(),0.0)
                }
            }
            println("Abrido!!")
        }
        leftDrawerOpen = !leftDrawerOpen;
    }

    fun toggleRightDrawer(){
        if(rightDrawerOpen){
            timeline {
                keyframe(Duration.seconds(.2)) {
                    keyvalue(root.hvalueProperty(),0.48)
                }
            }
        }else{
            timeline {
                keyframe(Duration.seconds(.2)) {
                    keyvalue(root.hvalueProperty(),1.0)
                }
            }
        }
        rightDrawerOpen = !rightDrawerOpen;
    }

}

class MyStyles : Stylesheet() {
    companion object {
        val leftBox by cssclass()
        val rightBox by cssclass()
        val redBackground by cssclass()
        val whiteBackground by cssclass()
    }

    init {
        leftBox {
            backgroundColor += c("#fe00")
            borderColor += box(c("#a1a1a1"))
            minWidth = 200.px
        }

        rightBox {
            backgroundColor += c("#fefefe")
            borderColor += box(c("#222222"))
            minWidth = 200.px
        }

        whiteBackground {
            backgroundColor += Color.WHITE
        }

        redBackground {
            backgroundColor += Color.RED
            borderColor += box(c(210, 178, 178))
        }
    }
}
