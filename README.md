# boomslang-c2-sim


<h2> What is Boomslang C2 Simulator? </h2>

**Boomslang** is a C2 simulator that provides an open-source tool to simulate new C2 doctrines and exercises. The main idea is to offer a simplistic modeling tool, enabling the design of complex exercises with different types of forces (air, land, navy, space, and cyber) and missions, and through his engine analyze the combination of individuals effects, providing to the analyst the cost, impact, and risk of the scenario. Moreover, it has an extensible design, enabling you to extend it to support your specific problem.

Its name is originated from a large, highly venomous snake in the family Colubridae. The boomslang can open its jaws as wide as 170 degrees when biting. Its venom is highly potent and contains a hemotoxin that disrupts a human's blood coagulation. That said, the venom is slow-acting, which helps buy time to obtain and anti-venom.

<h2> Boomslang C2 Simulator General Architecture</h2>

<a href="resources/img/boomslang_architecture_n.png">![Logo](resources/img/boomslang_architecture_n.png)</a>


Boomslang C2 simulator uses a **_publish-subscribe architecture_**. Each simulation engine publishes the status of their entities, as consume the simulation status produced by other engines to their internal calculus. **Exercise Manager** is responsible for the time management and synchronization of all machines involved in the simulation environment. During its initialization, Exercise Management read the plans saved in the JSON Database component, starts the _WeatherSimEngine(s)_, and loads the _KinectSimEngine(s) C2SimEngine(s), and CommunicationSimEngine(s)_, using previous information.

The main components are:_ KninectSimEngine, C2SimEngine, CommunicationSimEngine, and WeatherSimEngine_. 


The **KninectSimEngine** is responsible for calculating the movement of the entities in the simulation environment. The entity movement is impacted by the **_weather factor_** (provided by the WeatherSimEngine) and the **_health_** of the Kinect entity.


The **C2SimEngine** is responsible for performing the mission of the entity. Each entity has a group of capacities that produces effects in the simulation environment. The factors involved in the entity performance are the **_weather_** (from the WeatherSimEngine), the entity's **_health_**, and **_residual effects_** produced by the other entities.


The **CommunicationSimEngine** calculates the communication effects caused by interference in the channels existent in the simulation environment. Each entity to perform its mission requires communication capabilities. The entities can have **_active sensors_** and **passive ones**. The activity enables that entity causes anomalies in the channels, while the passive only suffers these effects.  The factors involved in the entity communication performance are the **_weather_** (from the WeatherSimEngine), the entity's **_radio health_**, and **_residual effects_** produced by the other entities.

The **WeatherSimEngine** is responsible for simulating several types of weather conditions, enabling that the other engines can calculate the impact of these situations on the performance of the entities.  From a practical perspective, the weather simulations are based on a probabilistic function that better represents the weather condition in the geographic place where the exercise will happen.

The **JSONDatabase** is a logical abstraction of the management of JSON files used in configuring all entities and engines in the simulation environment. There are two groups of files: **_database_** and **_exercise_** files. 

Thes **database files** are located in **resources/data/** and describes the basic information required to perform the simulation and consume internally by the engines. They are:
- **air_kinect_types.json**: it contains the type of air entities and its kinect parameters.
- **navy_kinect_types.json**: it contains the type of navy entities and its kinect parameters.
- **army_kinect_types.j.jsons.json.jsonon**: it contains the type of army entities and its kinect parameters.
- **space_kinect_types.j.json.json.jsonson**: it contains the type of space entities and its kinect parameters.
- **cyber_kinect_types.j.jsons.json.jsonon**: it contains the type of cyber entities and its kinect parameters.

<br>

- **air_c2_types.json**: it contains the type of air entities, its weapons and its mission (c2.json) parameters.
- **navy_c2_types.json**: it contains the type of navy entities, its weapons and its mission .json(c2) parameters.
- **army_c2_types.json**: it contains the type of army entities, its weapons and its mission (.jsonc2) parameters.
- **space_c2_types.json**: it contains the type of space entities, its weapons and its mission.json (c2) parameters.
- **cyber_c2_types.json**: it contains the type of cyber entities, its weapons and its mission .json(c2) parameters.

<br>

- **mission_types.json**: it contains the core taxonomy of missions.
- **air_c2_types.json**: it extends the mission_types, including the specific doctrine to perform  missions of air force entities.
- **navy_c2_types.json**: it extends the mission_types, including the specific doctrine to perform  missions of navy force entities.
- **army_c2_types.json**: it extends the mission_types, including the specific doctrine to perform  missions of army force entities.
- **space_c2_types.json**: it extends the mission_types, including the specific doctrine to perform  missions of space force entities.
- **cyber_c2_types.json**: it extends the mission_types, including the specific doctrine to perform  missions of cyber force entities.

<br>

- **resources/img**: it is a special folder, which contains the images used by the simulator.

<br>


The **exercise** files describe a specific exercise and it is saved inside the folder **resources/exe**. They are:

-**simulation.json**: it is the main file of simulation; all the other files are dependent on the information provided by this file.
- **plans.json**: it is the file that describes the entities involved in the exercise environment and its plans.
- **weather.json**: it is the file that describes the several distributions and other probabilistic information of weather scenarios.












