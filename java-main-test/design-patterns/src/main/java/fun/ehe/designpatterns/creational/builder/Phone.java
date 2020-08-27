package fun.ehe.designpatterns.creational.builder;

public class Phone {

    private Screen screen;
    private PCB pcb;
    private Speaker speaker;
    private Keyboard keyboard;
    private Shell shell;
    private Battery battery;

    private Phone(PhoneBuilder builder) {
        screen = builder.screen;
        pcb = builder.pcb;
        speaker = builder.speaker;
        keyboard = builder.keyboard;
        shell = builder.shell;
        battery = builder.battery;
    }

    public static class PhoneBuilder extends Builder<Phone> {
        private Screen screen;
        private PCB pcb;
        private Speaker speaker;
        private Keyboard keyboard;
        private Shell shell;
        private Battery battery;

        @Override
        public Builder<Phone> buildRequirement() {
            System.err.println("Which kind of phone is needed in the market?");
            return this;
        }

        @Override
        public Builder<Phone> buildDesign() {
            System.err.println("Phone drawing design");
            return this;
        }

        @Override
        public Builder<Phone> buildDevelopment() {
            battery = new Battery();
            keyboard = new Keyboard();
            pcb = new PCB();
            screen = new Screen();
            shell = new Shell();
            speaker = new Speaker();
            System.err.println("Manufacturing parts and assembling");
            return this;
        }

        @Override
        public Builder<Phone> buildTest() {
            System.err.println("Phone performance test");
            return this;
        }

        @Override
        public Phone getProduct() {
            return new Phone(this);
        }
    }

    public static class Screen {
    }

    public static class PCB {
    }

    public static class Speaker {
    }

    public static class Keyboard {
    }

    public static class Shell {
    }

    public static class Battery {
    }

    public Screen getScreen() {
        return screen;
    }

    public PCB getPcb() {
        return pcb;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Shell getShell() {
        return shell;
    }

    public Battery getBattery() {
        return battery;
    }

    @Override
    public String toString() {
        return "\nProduct is ".toString()
                //
                + super.toString() + ", it includes: " + "\n"
                //
                + "screen: " + this.screen + "\n"
                //
                + "pcb: " + this.pcb + "\n"
                //
                + "speaker: " + this.speaker + "\n"
                //
                + "keyboard: " + this.keyboard + "\n"
                //
                + "shell: " + this.shell + "\n"
                //
                + "battery: " + this.battery + "\n";
    }
}
