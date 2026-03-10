public class Bai2BuilderPatternDemo {
    public static void main(String[] args) {
        Computer computerWithBluetooth = new Computer.ComputerBuilder("1TB", "16GB")
                .setBluetoothEnabled(true)
                .build();

        Computer computerWithoutBluetooth = new Computer.ComputerBuilder("512GB", "8GB")
                .build();

        System.out.println("May tinh co Bluetooth:");
        System.out.println(computerWithBluetooth);

        System.out.println("\nMay tinh khong co Bluetooth:");
        System.out.println(computerWithoutBluetooth);
    }
}

class Computer {
    private final String hdd;
    private final String ram;
    private final boolean bluetoothEnabled;

    private Computer(ComputerBuilder builder) {
        this.hdd = builder.hdd;
        this.ram = builder.ram;
        this.bluetoothEnabled = builder.bluetoothEnabled;
    }

    @Override
    public String toString() {
        return "Computer{"
                + "HDD='" + hdd + '\''
                + ", RAM='" + ram + '\''
                + ", bluetoothEnabled=" + bluetoothEnabled
                + '}';
    }

    public static class ComputerBuilder {
        private final String hdd;
        private final String ram;
        private boolean bluetoothEnabled;

        public ComputerBuilder(String hdd, String ram) {
            this.hdd = hdd;
            this.ram = ram;
        }

        public ComputerBuilder setBluetoothEnabled(boolean bluetoothEnabled) {
            this.bluetoothEnabled = bluetoothEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
