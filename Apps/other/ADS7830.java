import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

public class ADS7830 {
    public static void main(String[] args) throws Exception {
        // Create I2C bus
        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, ADS7830 I2C address is 0x48
        I2CDevice device = bus.getDevice(0x4B);

		while(true){

			// Send command byte for single-ended input, channel 0 (example)
			device.write((byte) 0x84); // 1000 0100
			Thread.sleep(50);

			// Read 1 byte of data from ADS7830
			int raw_adc = device.read() & 0xFF; // mask to unsigned int

			// Output data to screen
			System.out.printf("Digital value of analog input: %d%n", raw_adc);
		}
    }
}
