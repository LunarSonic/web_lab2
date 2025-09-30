<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Lab2</title>
  <link rel="icon" type="image/png" href="images/duck.PNG">
  <link rel="stylesheet" href="style.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Old+Standard+TT:ital,wght@0,400;0,700;1,400&family=Playfair+Display:ital,wght@0,400..900;1,400..900&family=Viaoda+Libre&display=swap" rel="stylesheet">
</head>
<body>
<div id="main_container">
  <div id="background_image"></div>
  <div id="blur"></div>
  <header id="lab">
    <h1>Веб-программирование. Лабораторная работа №2. Вариант 63164</h1>
    <div id="container_with_image">
      <h2>Абдуллаева София Улугбековна. P3208</h2>
      <a href="https://github.com/LunarSonic/web_lab2" target="_blank">
        <img id="githubIcon" src="images/github.svg" alt="">
      </a>
    </div>
  </header>
  <div id="canvas_container">
    <form id="main_form" method="get" action="app">
      <div id="choice_of_x">
        <span class="label_name">Введите X:</span>
        <label><input type="text" id="x" name="x" placeholder="От -3 до 5"></label>
        <div class="error" id="errorX"></div>
      </div>
      <div id="choice_of_y">
        <span class="label_name">Выберите Y:</span>
        <label><input class="checkboxForY" type="checkbox" name="y" value="-5">-5</label>
        <label><input class="checkboxForY" type="checkbox" name="y" value="-4">-4</label>
        <label><input class="checkboxForY" type="checkbox" name="y" value="-3">-3</label>
        <label><input class="checkboxForY" type="checkbox" name="y" value="-2">-2</label>
        <label><input class="checkboxForY" type="checkbox" name="y" value="-1">-1</label>
        <label><input class="checkboxForY" type="checkbox" name="y" value="0">0</label>
        <label><input class="checkboxForY" type="checkbox" name="y" value="1">1</label>
        <label><input class="checkboxForY" type="checkbox" name="y" value="2">2</label>
        <label><input class="checkboxForY" type="checkbox" name="y" value="3">3</label>
        <div class="error" id="errorY"></div>
      </div>
      <div id="choice_of_r">
        <span class="label_name">Выберите R:</span>
        <label><input class="radioForR" type="radio" name="r" value="1">1</label>
        <label><input class="radioForR" type="radio" name="r" value="1.5">1.5</label>
        <label><input class="radioForR" type="radio" name="r" value="2">2</label>
        <label><input class="radioForR" type="radio" name="r" value="2.5">2.5</label>
        <label><input class="radioForR" type="radio" name="r" value="3">3</label>
        <div class="error" id="errorR"></div>
      </div>
      <div id="button_container">
        <button id="submit_button" type="submit">Подтвердить</button>
        <button id="clear_form_button" type="button">Очистить форму</button>
      </div>
      <div class="error" id="error"></div>
    </form>
    <form id="canvas_form" method="get" action="app" style="display:none;">
      <input type="hidden" name="x" id="canvas_x">
      <input type="hidden" name="y" id="canvas_y">
      <input type="hidden" name="r" id="canvas_r">
    </form>
    <div id="main_canvas">
      <canvas id="coordinate_plane" width="400" height="400"></canvas>
    </div>
  </div>
</div>
</body>
<script type="module" src="js/mainScript.js"></script>
</html>