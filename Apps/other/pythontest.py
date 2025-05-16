import smbus
import time

bus = smbus.SMBus(1)
addr = 0x4b

def read_adc(ch):
    cmd = 0x84 | (ch << 4)  # Single-ended mode, channel select
    bus.write_byte(addr, cmd)
    time.sleep(0.01)
    return bus.read_byte(addr)

while True:
    print(read_adc(0))
