# qthres<br />

With this small commandline tool written in java you can resolve a maidenhead locator and calculate the distance to it from your QTH. You can also enter a callsign and qthres will resolve its locator from qrzcq.com. Furthermore, qthres also has the ability to show the position and the distance of your locator/callsign on a map.<br />
<br />
<br />
**Dependencies**<br />
`JRE` and `Firefox` need to be installed on your system.
<br />
<br />
**Installation**<br />
Just execute the following command in the commandline:<br />
`cd && git clone https://www.github.com/BaumGuard/qthres && echo "alias qthres='java -jar ~/qthres/qthres.jar'" >> ~/.bashrc && nano ~/qthres/qth.txt`<br />
Substitude `0.0,0.0` by the coordinates (lat,lon) or the locator of your QTH. Make sure there are no spaces in the line.<br />
<br />
<br />
**How to use**<br />
`cd ~/qhtres`<br />
`java -jar qhtres.jar [locator/callsign] [options]`<br />

csrsl comes with four options which you can add after the locator/callsign:<br />
`help`: shows the help dialog<br />
`map`: displays the locator/callsign and the distance on a map (luftlinie.org)<br />
`nodist`: Only show coordinates<br />
`distonly`: Only show distance<br />
`imp`: Use miles for the distance instead of km<br />

Of course, you can also use more than one option.<br />

You can add the following line in `~/.bashrc` for accessing qhtres easier and faster from everywhere:<br />
`alias qhtres='java -jar ~/qhtres/qthres.jar'`<br />
Run `exec bash` or reboot your system after having included the line in `.bashrc`.<br />
