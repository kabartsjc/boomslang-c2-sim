<h1> What is the C2SimEngine? </h1>

The **C2SimEngine** is responsible for performing the mission of the entity. Each entity has a group of capacities that produces effects in the simulation environment. The factors involved in the entity performance are the weather (from the WeatherSimEngine), the entity's health, and residual effects produced by the other entities.

These database files are located in **resources/data/** and describes the basic information required to perform the simulation and consume internally by the engines. There are two types of files: **c2-types**, **weapons-types** and **mission-types**.

<h2>c2-types</h2>

- **air_c2_types.json**: it contains the type of air types and their parameters.
- **navy_c2_types.json**: it contains the type of navy types and their parameters.
- **army_c2_types.json**: it contains the type of army types and their parameters.
- **space_c2_types.json**: it contains the type of space types and their parameters.
- **cyber_c2_types.json**: it contains the type of cyber types and their parameters.

<h2>weapons-types</h2>

- **air_weapons.json**: it contains the type of air weapons and their parameters.
- **navy_weapons.json**: it contains the type of navy weapons and their parameters.
- **army_weapons.json**: it contains the type of army weapons and their parameters.
- **space_weapons.json**: it contains the type of space weapons and their parameters.
- **cyber_weapons.json**: it contains the type of cyber weapons and their parameters.

<h2>mission-types</h2>

- **mission_core.json**: it contains the core taxonomy of missions.
- **air_missions.json**: it contains the type of air missions and their parameters.
- **navy_missions.json**: it contains the type of navy missions and their parameters.
- **army_missions.json**: it contains the type of army missions and their parameters.
- **space_missions.json**: it contains the type of space missions and their parameters.
- **cyber_missions.json**: it contains the type of cyber missions and their parameters.
