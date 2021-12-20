
<h1> What is the Kinect Engine? </h1>

The **KinectSimEngine** is responsible for calculating the movement of the entities in the simulation environment. The entity movement is impacted by the weather factor (provided by the WeatherSimEngine) and the health of the Kinect entity.

These database files are located in **resources/data/** and describes the basic information required to perform the simulation and consume internally by the engines. 
- **air_kinect_types.json**: it contains the type of air entities and its kinect parameters.
- **navy_kinect_types.json**: it contains the type of navy entities and its kinect parameters.
- **army_kinect_types.json**: it contains the type of army entities and its kinect parameters.
- **space_kinect_types.json**: it contains the type of space entities and its kinect parameters.
- **cyber_kinect_types.jsons**: it contains the type of cyber entities and its kinect parameters.

Also, inside its configuration files, the engine point to the correct image folder, to define the graphical representation of the object defined in the resource file. 
- **resources/img**: it is a special folder, which contains the images used by the simulator.
