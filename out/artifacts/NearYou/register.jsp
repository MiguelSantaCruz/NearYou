<%--
  Created by IntelliJ IDEA.
  User: miguel
  Date: 21/01/22
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registar</title>
</head>
<style>* {box-sizing: border-box}

/* Add padding to containers */
.container {
    padding: 16px;
}

/* Full-width input fields */
input[type=text], input[type=password] {
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
}

/* Overwrite default styles of hr */
hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
}

/* Set a style for the submit/register button */
.registerbtn {
    background-color: #04AA6D;
    color: white;
    padding: 16px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
}

.registerbtn:hover {
    opacity:1;
}

/* Add a blue text color to links */
{
    color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
    background-color: #f1f1f1;
    text-align: center;
}</style>
<body>


<form action="registerAction" method="post">
    <div class="container">
        <h1>Registar</h1>
        <p>Preencha o formulário para criar uma conta</p>
        <hr>

        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Insira o email" name="email" id="email" required>

        <label for="username"><b>Nome de Utilizador</b></label>
        <input type="text" placeholder="Insira nome de utilizador" name="username" id="username" required>


        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Insira a Password" minlength="8" name="psw" id="psw" required>

        <label for="psw-repeat"><b>Repita Password</b></label>
        <input type="password" placeholder="Insira a Password" minlength="8" name="psw-repeat" id="psw-repeat" required>
        <hr>
        <button type="submit" class="registerbtn">Registar</button>
    </div>

    <div class="container signin">
        <p>Já possui uma conta? <a href="login">Sign in</a>.</p>
    </div>
</form>
</body>

</html>
