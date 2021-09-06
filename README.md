# qthres<br />
Click [here](https://github.com/BaumGuard/gqthres) to get to the newer graphical `gqthres`.<br /><br />
With this small commandline tool written in java you can resolve a maidenhead locator and calculate the distance to it from your QTH. You can also enter a callsign and qthres will resolve its locator from qrzcq.com. Furthermore, qthres also has the ability to show the position and the distance of your locator/callsign on a map. There is also the possibility to convert coordinates into a locator.<br />
<br />
<br />
**Dependencies**<br />
`JRE` and `Firefox` need to be installed on your system.
<br />
<br />
**Installation (Linux)**<br />
Just execute the following command in the commandline:<br />
`cd && git clone https://www.github.com/BaumGuard/qthres && echo "alias qthres='java -jar ~/qthres/qthres.jar'" >> ~/.bashrc && nano ~/qthres/qth.txt && exec bash`<br />
Replace `0.0,0.0` by the coordinates (lat,lon) or the locator (more inaccurate results) of your QTH. Make sure there are no spaces in the line.<br />
<br />
<br />
**Installation (Windows)**<br />
Download the zip file, unzip it in your home directory and rename it to "qthres". Enter your coordinates or your locator (less precise) in qth.txt and save it. Make sure that there are no spaces in it, since that might cause a malfunction of the map feature.<br />
For executing, navigate to the folder qthres in your home directory and start qthres with `java -jar qthres.jar`. Enter the locator/callsign/coordinates and the options you want to use and press enter. For further information just read the "How to use" section.<br />
<br />
<br />
**How to use**<br />
Linux: `qthres [locator/callsign/coordinates(lat,lon)] [options]`<br />
Windows: `java -jar qthres.jar [locator/callsign/coordinates(lat,lon)] [options]`<br />

qthres comes with four options which you can add after the locator/callsign:<br />
`help`: Show the help dialog<br />
`map`: Show the locator/callsign and the distance on a map (luftlinie.org)<br />
`qrzcq`: Open the webpage (qrzcq.com) of the callsign<br />
`qrz`: Open the webpage (qrz.com) of the callsign<br />
`nodist`: Only show coordinates<br />
`distonly`: Only show distance<br />
`imp`: Use miles for the distance instead of km<br />
`swap`: Output the coordinates in the format lon,lat<br />

Of course, you can also use more than one option.<br />
