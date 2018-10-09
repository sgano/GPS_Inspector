/*
    This file is part of GpsInspector.

    GpsInspector is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    GpsInspector is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with GpsInspector.  If not, see <http://www.gnu.org/licenses/>.

 */

/*
 *  Copyright(C) 2009 Shawn Gano shawn@gano.name
*/

package name.gano;

import java.util.Hashtable;
import name.gano.gps.GPSSimulatedNmeaDevice;
import org.dinopolis.gpstool.gpsinput.GPSDevice;
import org.dinopolis.gpstool.gpsinput.GPSRawDataListener;
import org.dinopolis.gpstool.gpsinput.GPSSerialDevice;
import org.dinopolis.gpstool.gpsinput.nmea.GPSNmeaDataProcessor;

/**
 *
 * @author Shawn
 */
public class TestGPS
{
    static boolean simulation = true;

    static GPSNmeaDataProcessor gps_data_processor;
    static int serial_port_speed = 4800;
    static String serial_port_name = "COM3";

    public static void main(String[] args)
    {
        gps_data_processor = new GPSNmeaDataProcessor();

        gps_data_processor.addGPSRawDataListener(
        new GPSRawDataListener()
        {
          public void gpsRawDataReceived(char[] data, int offset, int length)
          {
            System.out.print("RAWLOG: "+new String(data,offset,length));
          }
        });

          // Define device to read data from
        GPSDevice gps_device;
        Hashtable environment = new Hashtable();

        if (serial_port_name != null)
        {
            environment.put(GPSSerialDevice.PORT_NAME_KEY, serial_port_name);
        }
        if (serial_port_speed > -1)
        {
            environment.put(GPSSerialDevice.PORT_SPEED_KEY, new Integer(serial_port_speed));
        }

        if(simulation)
        {
            gps_device = new GPSSimulatedNmeaDevice();
        }
        else
        {
            gps_device = new GPSSerialDevice();
        }




        try
        {
            // set params needed to open device (file,serial, ...):
            gps_device.init(environment);
            // connect device and data processor:
            gps_data_processor.setGPSDevice(gps_device);
            gps_data_processor.open();

            System.out.println("GPSInfo:");
            String[] infos = gps_data_processor.getGPSInfo();
            for (int index = 0; index < infos.length; index++)
            {
                System.out.println(infos[index]);
            }

            // sleep for a while then turn off GPS
            Thread.sleep(15 * 1000);
            gps_data_processor.close(); // must close otherwise it keeps on running


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    } //main

}
