import {redraw, center, scale} from "./canvas.js"
import {Validation} from "./classes/Validation.js"
import {FormManager} from "./classes/FormManager.js"
import {CanvasHandler} from "./classes/CanvasHandler.js"
import {DOMHandler} from "./classes/DOMHandler.js"

document.addEventListener('DOMContentLoaded', () => {
    const dom = new DOMHandler()
    const validation = new Validation(
        dom.xInput, dom.yCheckbox, dom.yValueForCanvas,
        dom.errorField, dom.errorX, dom.errorY, dom.errorR)

    const formManager = new FormManager(
        dom.mainForm, dom.clearFormButton, dom.rChoice,
        dom.yCheckbox, dom.yValueForCanvas, redraw, validation)

    const canvasHandler = new CanvasHandler(
        dom.canvas, dom.xInput, dom.yValueForCanvas, dom.yCheckbox,
        dom.mainForm, dom.errorField, validation, center, scale)
    redraw()
})
