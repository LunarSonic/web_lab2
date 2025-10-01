export class DOMHandler {
    constructor() {
        this.yCheckbox = document.querySelectorAll('#choice_of_y input[type="checkbox"]')
        this.rChoice = document.querySelectorAll('#choice_of_r input[type="radio"]')

        this.xInput = document.getElementById('x')
        this.yValueForCanvas = document.getElementById('yCanvas')
        this.mainForm = document.getElementById("main_form")
        this.clearFormButton = document.getElementById('clear_form_button')
        this.canvas = document.getElementById("coordinate_plane")

        this.errorField = document.getElementById("error")
        this.errorX = document.getElementById("errorX")
        this.errorY = document.getElementById("errorY")
        this.errorR = document.getElementById("errorR")
    }
}