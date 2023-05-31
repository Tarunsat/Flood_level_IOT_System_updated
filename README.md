# FlowMap - Real-time IoT Data Visualization

FlowMap is an IoT application that leverages Raspberry Pis to send real-time data to Firebase. This data is then processed and displayed on an Android app, graphed onto a Google Map with a geofence. This powerful combination enables seamless visualization and monitoring of IoT data in a geographical context.

## Features

- Real-time data streaming: Raspberry Pis send continuous data to Firebase, ensuring up-to-date information.
- Firebase integration: Firebase provides a reliable and scalable real-time database for storing and retrieving IoT data.
- Data processing: The application processes the incoming data, extracting relevant information for visualization.
- Google Maps integration: The Android app integrates with Google Maps, enabling data visualization on an interactive map.
- Graphical representation: The data is graphed onto the Google Map, allowing users to quickly interpret and analyze IoT data.
- Geofencing capabilities: The app supports the creation of geofences, enabling users to set boundaries and receive alerts based on IoT data within specified areas.

## Installation

1. Clone the repository to your local machine.
2. Set up Firebase for your project and obtain the necessary credentials.
3. Update the Android app's configuration with your Firebase credentials.
4. Build and install the Android app on your device or emulator.
5. Set up the Raspberry Pis with appropriate sensors and the code to send data to Firebase.
6. Ensure that the Raspberry Pis have internet connectivity to communicate with Firebase.

## Usage

1. Launch the Android app on your device.
2. Grant necessary permissions for location access and notifications.
3. The app will display a Google Map with data points representing the real-time IoT data.
4. Customize the visualization by adjusting filters, zooming in/out, or switching between map views.
5. Create geofences on the map to monitor IoT data within specific areas.
6. Receive alerts or notifications based on the data within the defined geofences.
7. Interact with the data points or geofences to access detailed information or perform specific actions.
8. Analyze and visualize real-time IoT data on the go with FlowMap!

## Acknowledgments

- This application uses Firebase for real-time data storage and retrieval.
- Google Maps SDK provides the map visualization capabilities.
- Special thanks to the Jonathan Joby and others for their valuable contributions.

Feel free to explore the code, contribute, and enhance FlowMap to suit your specific IoT data visualization needs. Happy mapping!
