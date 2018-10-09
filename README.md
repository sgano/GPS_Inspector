# GPS_Inspector
Process and display information from a GPS device using java. 

For more information (and screenshots) see: https://www.gano.name/shawn/GpsInspector/

Overview
I wrote GPS Inspector as an experiment to learn more about GPS and how it could be used within JAVA. I figured others might find it useful or interesting so I have posted the source (GPLv3) and binaries here. It uses parts of other java libraries including: World Wind Java, Gpsylon, JFreeChart, and RXTX (serial device communications). I used the NetBeans IDE for development. The program should work for any NMEA (spec) compatible GPS device. I used the BU-353 GPS Receiver which I got for a really good price on amazon.com. Some modification of the code would be needed to get it to work with Garmin Devices. I have also used the program on both windows and linux machines without any trouble.

List of the major features:

- Serial port scan to make it easier to find GPS devices
- Ability to capture raw NMEA data to a file and to replay/simulate the data
- Built in simulated NMEA data (mostly used for debugging)
- Displays lots of GPS output data: position, fix info, fix quality, dilution of precision, speed, heading, and fix time
- Destination direction and distance pointing, with the ability to search for destinations using plain text searches
- 3D/2D plotting of location (and optionally path history) with streaming global satellite imagery layers (via World Wind Java)
- Bulk imagery downloader for caching imagery for offline use.
- Bar plots of signal to noise ratios of satellites (tracked and in the GPS almanac)
- Polar plot showing the azimuth/elevation locations of the GPS satellites
- Raw NMEA data display
- Downloads/Install


To run GPS Inspector in windows you could just double click on the included .exe file, for other systems you can try double clicking on the "GPSInspector.jar" file. If that doesn't work you can run the program from the command line with these options: 
java -jar GPSInspector.jar -Djava.library.path=. -Xms256m -Xmx1024m

By: Shawn Gano, 29 April 2009
