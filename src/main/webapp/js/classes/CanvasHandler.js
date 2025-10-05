export class CanvasHandler {
    constructor(canvas, xInput, yValueForCanvas, yCheckbox, mainForm, errorField, validator, center, scale) {
        this.canvas = canvas
        this.xInput = xInput
        this.yValueForCanvas = yValueForCanvas
        this.yCheckbox = yCheckbox
        this.mainForm = mainForm
        this.errorField = errorField
        this.validator = validator
        this.center = center
        this.scale = scale
        canvas.addEventListener('click', this.handleClick.bind(this))
    }

    handleClick(event) {
        const rRadio = document.querySelector('#choice_of_r input[type="radio"]:checked')
        if (!rRadio) {
            this.validator.showMessage(this.errorField, "Необходимо сначала выбрать радиус R!")
            return
        }
        const rect = this.canvas.getBoundingClientRect()
        const offset = 2
        const x_px = event.clientX - rect.left - offset
        const y_px = event.clientY - rect.top - offset
        const realX = (x_px - this.center) / this.scale
        const realY = -((y_px - this.center) / this.scale)
        const xFloat = parseFloat(realX.toFixed(4))
        const yFloat = parseFloat(realY.toFixed(4))
        if (xFloat <= -3 || xFloat >= 5) {
            this.validator.showMessage(this.errorField, `Координата X со значением ${xFloat.toString()} находится вне диапазона (-3, 5)`)
            return
        }
        if (yFloat < -5 || yFloat > 3) {
            this.validator.showMessage(this.errorField, `Координата Y со значением ${yFloat.toString()} находится вне диапазона [-5, 3]`)
            return
        }
        this.xInput.value = xFloat
        this.yValueForCanvas.value = yFloat
        this.yCheckbox.forEach(y => y.checked = false)
        this.validator.showMessage(this.errorField, "")
        this.mainForm.submit()
    }
}