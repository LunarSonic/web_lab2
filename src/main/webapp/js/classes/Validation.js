export class Validation {
    constructor(xInput, yCheckbox, yValueForCanvas, errorField, errorX, errorY, errorR) {
        this.xInput = xInput
        this.yCheckbox = yCheckbox
        this.yValueForCanvas = yValueForCanvas
        this.errorField = errorField
        this.errorX = errorX
        this.errorY = errorY
        this.errorR = errorR
        this.floatPattern = /^-?(?:0|[1-9][0-9]*)(?:\.[0-9]+)?$/
    }

    showMessage(element, message) {
        element.onanimationend = null
        if (message) {
            element.hidden = false
            element.style.animation = 'fadeInAndFadeOut 3s'
            element.textContent = message
            element.onanimationend = () => {
                element.hidden = true
                element.textContent = ""
            }
        } else {
            element.hidden = true
            element.style.animation = 'none'
            element.textContent = ""
        }
    }

    validateX() {
        const x = this.xInput.value
        if (x === '') {
            this.showMessage(this.errorX, "Не введена координата X!")
            return false
        } else if (!this.floatPattern.test(x)) {
            this.showMessage(this.errorX, "Координата X должна быть числом!")
            return false
        }
        const xFloat = parseFloat(x)
        if (xFloat <= -3 || xFloat >= 5) {
            this.showMessage(this.errorX, "Координата X должна быть в диапазоне (-3; 5)")
            return false
        }
        this.showMessage(this.errorX,"")
        return true
    }

    validateR() {
        const selectedR = document.querySelector('input[type="radio"]:checked')
        if (!selectedR) {
            this.showMessage(this.errorR, "Необходимо выбрать координату R!")
            return false
        } else {
            this.showMessage(this.errorR, "")
            return true
        }
    }

    validateYCanvas() {
        const yValue = this.yValueForCanvas.value
        if (yValue.length === 0) return true
        const yFloat = parseFloat(yValue)
        if (isNaN(yFloat)) {
            this.showMessage(this.errorField, "Координата Y должна быть числом!")
            return false
        } else if (yFloat < -5 || yFloat > 3) {
            this.showMessage(this.errorField, "Координата Y должна быть в диапазоне [-5; 3]")
            return false
        }
        this.showMessage(this.errorField, "")
        return true
    }

    validateY() {
        if (this.yValueForCanvas.value.length > 0) {
            if (this.validateYCanvas()) {
                this.showMessage(this.errorField, '')
                return true
            } else {
                return false
            }
        }
        const checkedY = Array.from(this.yCheckbox).filter(y => y.checked)
        if (checkedY.length === 0) {
            this.showMessage(this.errorY, "Необходимо выбрать координату Y!")
            return false
        } else {
            this.showMessage(this.errorY, "")
            return true
        }
    }
}