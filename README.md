# Logging Interface 📝💻

Logging Interface is a Java-based project that utilizes WebSockets to reflect real-time updates from a log file onto a browser window. It offers a seamless experience where updates are instantly displayed without the need to refresh the page.

## Features

✨ Real-time updates: Instantly reflects changes made to the log file on the browser interface.  
📜 Last 10 lines preview: Displays the last 10 lines of the log file upon opening the endpoint in a browser.  
🌐 WebSocket technology: Uses WebSockets for efficient and low-latency communication between the server and client.  
🔒 Secure: Follows best practices to ensure secure communication between the server and client.  

## Usage

1. Clone this repository:
   ```bash
   git clone https://github.com/your_username/logging-interface.git
    ```
   
2. Navigate to the project directory:
   ```bash
   cd logging-interface
   ```
   
3. Deploy the project on your Tomcat server. You can do this by copying the project's WAR file to the webapps directory of your Tomcat installation.

4. Start your Tomcat server.

5. Open your browser and navigate to http://localhost:8080/logging_interface_war_exploded/ to view the logging interface.

## Dependencies
- Java (JDK)
- WebSocket API (included in Java EE or can be added as a dependency)
- Tomcat server
- Browser with WebSocket support (e.g., Chrome, Firefox, Edge)

## Contributing
Contributions are welcome! Feel free to open issues or pull requests for any improvements or new features you'd like to see.
