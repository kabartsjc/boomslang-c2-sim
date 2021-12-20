
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

<h2>General Parameters</h2>

- "id": it is unique number (integer) that identifies the type of kinect entity (ex.: 1)
- "name": it is a unique name that caractherizes the entity (ex: "F35A")
- "type": identifies the type of entity (ex: "FIGHTER_5G")
- "reference": Internet reference, where can be search more information about the entity type (ex: "https://en.wikipedia.org/wiki/Lockheed_Martin_F-35_Lightning_II")
- "image_file": it identifies the name of picture (**resources/img**) that is used in this configuration (ex: "FIGHTER_5G.png")
- "kinect_resilience": it is a float number that identifies the resilience that aircraft has when suffer an opposite effect against its movemment (ex: 0.85)
- "autonomy": means how many time the entity can operate (or flight) in sec (ex: 5109)


<h2>Parameters of air_kinect_types.json</h2>
- "descendent_speed": defines the descendent speed in m/s (ex: 400)
- "cruize_speed": defines the cruize speed - m/s (ex: 548)
- "ascedent_speed":  defines the ascendent speed in m/s (ex: 400)
- "ascedent_rate": defines the rate of the entity during the takeoff or climb in m/s (ex: 100)
- "descedent_rate": defines the rate of the entity during the landing or descendent operations in m/s (ex: 100)

<h2>Parameters of navy_kinect_types.json</h2>
- "diving_speed": defines the diving speed (submarine operations) in m/s (ex: 40)
- "surface_cruize_speed": defines the cruize speed in the surface - m/s (ex: 548)
- "underwater_cruize_speed":  defines the cruize speed underwater (submarine opperations) speed in m/s (ex: 400)
- "surfacing_speed": defines the ascedent speed - surfacing (submarine operations) in m/s (ex: 100)
- "diving_rate": defines the rate of the entity during the diving operations (submarine) in m/s (ex: 100)
- "surfacing_rate": defines the rate of the entity during the surfacing operations (submarine) in m/s (ex: 100)

<h2>Parameters of army_kinect_types.json</h2>
- "rough_road_speed": defines the speed on the rough terrain (like sand, montain, etc) in m/s (ex: 40)
- "plain__road_speed": defines the speed on plain terrain (roads) in m/s (ex: 40)
- "city__road_speed": defines the speed in the cities (streets) in m/s (ex: 40)

