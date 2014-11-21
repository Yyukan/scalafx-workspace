import javafx.event.ActionEvent
import javafx.event.EventHandler

import scalafx.scene.input.KeyCombination
import scalafx.scene.layout.HBox

import scalafx.application.{Platform, JFXApp}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Side
import scalafx.scene
import scalafx.scene.Scene
import scalafx.scene.control.TabPane.TabClosingPolicy
import scalafx.scene.control._
import scalafx.scene.layout.BorderPane
import scene.text.Text

/**
 * Entry point to the Application
 */
object WorkspaceApp extends JFXApp {

  val rootTreeItem = new TreeItem[String]("Sessions") {
    expanded = true
    //children = EnsembleTree.create().getTree
  }

  val controlsView = new TreeView[String]() {
    editable = false
    root = rootTreeItem
    id = "page-tree"
  }

  val centerPane = new TabPane {
    tabs = Seq(
      new Tab {
        text = "Tab 1"
      },
      new Tab {
        text = "Tab 2"
      },
      new Tab {
        text = "Tab 3"
      }
    )
    tabClosingPolicy = TabClosingPolicy.UNAVAILABLE
    side = Side.TOP
  }

  val scrollPane = new ScrollPane {
    fitToWidth = true
    fitToHeight = true
    id = "page-tree"
    content = controlsView
  }

  val splitPane = new SplitPane {
    dividerPositions = 0
    id = "page-splitpane"
    items.addAll(scrollPane, centerPane)
  }

  // workspace menu
  val menuBar = new MenuBar {
    useSystemMenuBar = false
    menus = Seq(new Menu("File") {
      items = List(
        new MenuItem("Connect") {
        },
        new MenuItem("Quit") {
          accelerator = KeyCombination.keyCombination("Ctrl+X")
          onAction = new EventHandler[ActionEvent] {
            def handle(t: ActionEvent) = {
              Platform.exit()
            }
          }
        }
      )
    })
  }

  // workspace status bar
  val statusBar = new HBox {
    content = Seq(
      new Text {
        text = "This is status bar area"
      }
    )
  }

  // main stage definition
  stage = new PrimaryStage {
    title = "Workspace Application title"
    scene = new Scene(1020, 700) {
      root = new BorderPane {
        top = menuBar
        center = splitPane
        bottom = statusBar
      }
    }
  }
}
