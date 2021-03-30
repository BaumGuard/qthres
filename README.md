# qthres<br />

With this small commandline tool written in java you can resolve a maidenhead locator and calculate the distance to it from your QTH. You can also enter a callsign and qthres will resolve its locator from qrzcq.com. Furthermore, qthres also has the ability to show the position and the distance of your locator/callsign on a map.<br />
<br />
<br />
**Dependencies**<br />
`JRE` and `Firefox` need to be installed on your system.
<br />
<br />
**Installation**<br />
`cd`<br />
`git clone https://www.github.com/BaumGuard/qthres`<br />
This will download the source folder of `qhtres` into your home directory.<br />

You don't have to compile the source code, since I've done that already :-).<br />

The executable file is called `qhtres.jar`<br />

Enter your coordinates or your locator in `~/qhtres/qth.txt`, because otherways you will get wrong distances.<br />
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
