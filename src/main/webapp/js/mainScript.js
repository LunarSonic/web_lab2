import {redraw, center, scale} from "./canvas.js";
const yCheckbox = document.querySelectorAll('#choice_of_y input[type="checkbox"]')
const xInput = document.getElementById('x')
const rChoice = document.querySelectorAll('#choice_of_r input[type="radio"]')
const mainForm = document.getElementById("main_form")
const clearFormButton = document.getElementById('clear_form_button')
const canvas = document.getElementById("coordinate_plane")
const canvasForm = document.getElementById("canvas_form")
const canvasXInput = document.getElementById("canvas_x")
const canvasYInput = document.getElementById("canvas_y")
const canvasRInput = document.getElementById("canvas_r")
const errorField = document.getElementById("error")
const floatPattern = /^-?(?:0|[1-9][0-9]*)(?:\.[0-9]+)?$/
let lastCheckedY = null

document.addEventListener('DOMContentLoaded', () => {
    redraw()
})

function chooseOnlyOneCheckbox(event) {
    const selectedCheckbox = event.target
    if (selectedCheckbox.checked) {
        if (lastCheckedY !== selectedCheckbox && lastCheckedY) {
            lastCheckedY.checked = false
        }
        lastCheckedY = selectedCheckbox
    } else {
        if (lastCheckedY === selectedCheckbox) {
            lastCheckedY = null
        }
    }
}
yCheckbox.forEach(y => {
    y.addEventListener('change', chooseOnlyOneCheckbox)
})

function handleClick(event) {
    const rRadio = document.querySelector('#choice_of_r input[type="radio"]:checked')
    if (!rRadio) {
        showMessage(errorField, "Необходимо сначала выбрать радиус R!")
    }
    const r = rRadio.value
    const rect = canvas.getBoundingClientRect()
    const x_px = event.clientX - rect.left
    const y_px = event.clientY - rect.top
    const realX = (x_px - center) / scale
    const realY = -((y_px - center) / scale)
    const yRounded = Math.round(realY)
    const xFloat = parseFloat(realX.toFixed(4))
    if (xFloat < -3 || xFloat > 5) {
        showMessage(errorField, `Координата X со значением ${xFloat.toString()} находится вне диапазона (-3, 5)`)
        return
    }
    if (yRounded < -5 || yRounded > 3) {
        showMessage(errorField, `Координата Y со значением ${yRounded.toString()} находится вне диапазона [-5, 3]`)
        return
    }
    canvasXInput.value = xFloat.toFixed(4)
    canvasYInput.value = yRounded.toString()
    canvasRInput.value = r
    showMessage(errorField, "")
    canvasForm.submit()
}
canvas.addEventListener('click', handleClick)

function showMessage(element, message) {
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

function clearForm() {
    mainForm.reset()
    redraw()
}
clearFormButton.addEventListener('click', clearForm)


function validateForm(event) {
    const isXValid = validateX(xInput.value)
    const isYValid = validateY()
    const isRValid = validateR()
    if (!isXValid || !isYValid || !isRValid) {
        event.preventDefault()
        return false
    }
    return true
}
mainForm.addEventListener('submit', validateForm)


const validateY = function() {
    const checkedY = document.querySelectorAll('input[type="checkbox"]:checked')
    const yErrorField = document.getElementById("errorY")
    if (checkedY.length === 0) {
        showMessage(yErrorField, "Необходимо выбрать координату Y!")
        return false
    } else {
        showMessage(yErrorField, "")
        return true
    }
}

const validateX = function(x) {
    const xErrorField = document.getElementById("errorX")
    if (x === '') {
        showMessage(xErrorField, "Не введена координата X!")
        return false
    } else if (!floatPattern.test(x)) {
        showMessage(xErrorField, "Координата X должна быть числом!")
        return false
    }
    const xFloat = parseFloat(x)
    if (xFloat < -3 || xFloat > 5) {
        showMessage(xErrorField, "Координата X должна принимать значение от -3 до 5!")
        return false
    } else {
        showMessage(xErrorField, "")
        return true
    }
}

const validateR = function() {
    const rErrorField = document.getElementById("errorR")
    const selectedR = document.querySelector('input[type="radio"]:checked')
    if (!selectedR) {
        showMessage(rErrorField, "Необходимо выбрать координату R!")
        return false
    } else {
        showMessage(rErrorField, "")
        return true
    }
}

function handleRadioChange(event) {
    const currentRadio = event.target
    const currentR = parseFloat(currentRadio.value)
    redraw(currentR)
}
rChoice.forEach(radio => {
    radio.addEventListener('change', handleRadioChange)
})