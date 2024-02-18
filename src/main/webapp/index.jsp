<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<style>
    body {
        background-color: black;
        color: lawngreen;
    }
</style>
<script>
    function updateLogs() {
        const mainBlock = document.getElementById("main-block");
        const socket = new WebSocket("ws://localhost:8080/logging_interface_war_exploded/logpub")
        socket.onmessage = (event) => {
            try {
                mainBlock.append(event.data);
            } catch (e) {
                console.log("exception in onmessage", e);
            }
        };

        socket.onerror = (event) => {
            mainBlock.append("Error occurred !!!! " + event.data);
        };

        socket.onclose = () => {
            mainBlock.append("Server connection closed");
        };
    }
</script>
<body onload="updateLogs()">
<pre id="main-block"></pre>
</body>
</html>