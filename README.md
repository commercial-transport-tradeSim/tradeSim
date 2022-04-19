# tradeSim - Commercial Travel Demand Simulation

tradeSim is a commercial activity scheduler for travel demand simulations. The input data for the model consist of businesses with their geo-location and vehicle fleet and private households within the model area. 
For each vehicle, tradeSim generates an activity schedule such that each activity includes information on the type of activity, their duration, the distance to the destination, and the type of destination.

## Modelled Variables and their Attribute Levels

### Activity Purposes

The activity purpose is modelled using a nested logit model in which on the upper level it is determined if a private activity (1), the return to the vehicle's business (2) or a commercial activity is conducted. If a commercial activity is chosen, then on the lower level of the nested structure, the specified purpose (3-6) is determined. 

| Activity  |                         |
| ----------|-------------------------|
| 1         | private                 |
| 2         | return to own business  |
| 3         | delivering goods        |
| 4         | providing a service     |
| 5         | dropping off people     |
| 6         | other commercial        |


### Industry Sectors

KiD 2010 differentiates between the WZ 2008 industry sectors. However, there are many classes, some only have few observations in the data and cannot be used for model specification in their original format. Thus, industry sectors are clustered as follows:

- *Industry*: A, B, C, D, E, F
- *Service*: G, H, I, J, K, L, M, N, Q, S
- *Other*: P, R, T, U, O

### Vehicle Classes

For the same reasons as explained above (see industry sectors), we have clustered the vehicle types according to the following structure:
- *light vehicles [1]*:
  - motorcycles [1]
  - passenger vehicles [2]
- *medium vehicles [2]*
  - trucks with a payload capacity of 3.5t [3]
- *heavy vehicles [3]*
  - trucks with a payload capacity over 3.5t [4]
  - semi trailer trucks [5]
  - Busses [6]
  - agricultural tractors [7]
  - other tractors [8]
  - other vehicles [9]
  
  
  ### Time of Day Groups:
  
  - morning:  between 4am and before 11am
  - day:      between 11am and before 9pm
  - night:    between 9pm and before 4am

# Getting Started
 - Install Java 17 (tested for: OpenJDK Runtime Environment (build 17+35-2724))
 - Clone this repository
 - Run 'tradeSim/run/setup.bat' or 'tradeSim/run/setup.sh': this starts the gradle wrapper, refreshes all dependencies and builds the code
 - For each .yaml configuration file, an executable .bat and .sh file are generated in 'tradeSim/run' (it should now additionally contain 'run-config_template_Simulation.sh' and 'run-config_template_Simulation.bat')
 - Run one of these files to test a template project. After it has finished a new folder 'tradeSim/example/output/results/template' should appear containing the simulation results.

# Simulating a Custom Project
 - Place your project specific data and configuration files under: 'tradeSim/example/project'
 - Run either of the setup files 'tradeSim/run/setup.bat' or 'tradeSim/run/setup.sh'
 - Run one of the generated .bat / .sh files of the projects config files
 - The result folder is specified in the executed config file (relative path under 'tradeSim/example')

## Suggested File Structure
  - tradeSim/example/project
    - config (.yaml files here)
    - data (business.csv here)
      - distributions (distribution .csv files here)
      - parameters (parameter .txt files for choice models here)
      - opportunities (opportunity and population .csv files here)

# Browsing JavaDoc
 - Run either 'tradeSim/run/browseJavaDoc.bat' or 'tradeSim/run/browseJavaDoc.sh' which generate the javadoc html and opens it in the default browser

