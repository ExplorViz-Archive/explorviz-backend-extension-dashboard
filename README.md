# ExplorViz Backend Dashboard Extension

This repository holds an extension for [ExplorViz Backend](https://github.com/ExplorViz/explorviz-backend) that adds an dashboard to explorviz. this dashboard can be used
to monitore a application better. It has components named widgets that supports you in the process of monitoring a application.

The related frontend extension is [explorviz-frontend-extension-dashboard](https://github.com/ExplorViz/explorviz-frontend-extension-dashboard).


## Requirements
- [ExplorViz Frontend](https://github.com/ExplorViz/explorviz-frontend)
- [ExplorViz Backend](https://github.com/ExplorViz/explorviz-backend)
- [Explorviz-Frontend-Extension-Dashboard](https://github.com/ExplorViz/explorviz-frontend-extension-dashboard)

## Installation
1. Follow the [Eclipse Setup](https://github.com/ExplorViz/explorviz-backend#eclipse-setup) of the [ExplorViz Backend](https://github.com/ExplorViz/explorviz-backend)
2. Clone this repository
3. Import project into eclipse: via `Import -> Gradle -> Existing Gradle project -> path/to/explorviz-backend-extension-dashboard`
4. Start **explorviz-backend-extension-dashboard** via Eclipse Tab: `Gradle Tasks -> explorviz-backend-extension-dashboard -> gretty -> appStart`
5. Setup and start [explorviz-frontend](https://github.com/ExplorViz/explorviz-frontend) with the installed [explorviz-frontend-extension-dashboard](https://github.com/ExplorViz/explorviz-frontend-extension-dashboard)
