<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        form {border: 3px solid #f1f1f1;}

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            background-color: #04AA6D;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }

        .gobackbtn {
            width: auto;
            padding: 10px 18px;
            background-color: #f44336;
        }

        .imgcontainer {
            text-align: center;

        }

        img.avatar {
            width: 10%;
        }

        .container {
            padding: 16px;
        }

        span.psw {
            float: right;
            padding-top: 16px;
        }

        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }
            .cancelbtn {
                width: 100%;
            }
        }
    </style>
</head>
<body>

<h2>Login</h2>

<form action="loginAction" method="post">
    <div class="imgcontainer">
        <img src="nearyou_logo.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
        <%--@declare id="uname"--%><label for="uname"><b>Email</b></label>
        <input type="text" placeholder="Insira o email" name="uname" required>

        <label for="psw"><b>Password</b></label>
        <input id="password" type="password" placeholder="Insira Password" name="psw" required>

        <button type="submit">Login</button>
    </div>
    <div class="container" style="background-color:#f1f1f1">
        <input type="button" value="Voltar" onclick="history.back()">
        <span class="psw">Esqueceu a <a href="#">password?</a></span>
    </div>
</form>

</body>
</html>