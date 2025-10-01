export class FormManager {
    constructor(mainForm, clearFormButton, rChoice, yCheckbox, yValueForCanvas, redrawCanvas, validator) {
        this.mainForm = mainForm
        this.clearFormButton = clearFormButton
        this.rChoice = rChoice
        this.yCheckbox = yCheckbox
        this.yValueForCanvas = yValueForCanvas
        this.redrawCanvas = redrawCanvas
        this.validator = validator
        this.lastCheckedY = null
        this.initializeEventListeners()
    }

    initializeEventListeners() {
        this.clearFormButton.addEventListener('click', this.clearForm.bind(this))
        this.rChoice.forEach(radio => {
            radio.addEventListener('change', this.handleRadioChange.bind(this))
        })
        this.yCheckbox.forEach(y => {
            y.addEventListener('change', this.chooseOnlyOneCheckbox.bind(this))
        })
        this.mainForm.addEventListener('submit', this.handleSubmit.bind(this))
    }

    clearForm() {
        this.mainForm.reset()
        this.yValueForCanvas.value = ''
        this.lastCheckedY = null
        this.redrawCanvas()
    }

    handleRadioChange(event) {
        const currentRadio = event.target
        const currentR = parseFloat(currentRadio.value)
        this.redrawCanvas(currentR)
    }

    chooseOnlyOneCheckbox(event) {
        const selectedCheckbox = event.target
        if (selectedCheckbox.checked) {
            if (this.lastCheckedY !== selectedCheckbox && this.lastCheckedY) {
                this.lastCheckedY.checked = false
            }
            this.lastCheckedY = selectedCheckbox
            this.yValueForCanvas.value = ''
        } else {
            if (this.lastCheckedY === selectedCheckbox) {
                this.lastCheckedY = null
            }
        }
    }

    handleSubmit(event) {
        const isXValid = this.validator.validateX()
        const isYValid = this.validator.validateY()
        const isRValid = this.validator.validateR()
        if (!isXValid || !isYValid || !isRValid) {
            event.preventDefault()
            return false
        }
        return true
    }
}