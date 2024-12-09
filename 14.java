// Интерфейс Command
interface Command {
    void execute();
}

// Класс, представляющий устройство (например, телевизор)
class TV {
    public void turnOn() {
        System.out.println("TV is ON");
    }

    public void turnOff() {
        System.out.println("TV is OFF");
    }
}

// Команда включения устройства
class TurnOnCommand implements Command {
    private TV tv;

    public TurnOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }
}

// Команда выключения устройства
class TurnOffCommand implements Command {
    private TV tv;

    public TurnOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }
}

// Класс пульта дистанционного управления
class RemoteControl {
    private Command turnOnCommand;
    private Command turnOffCommand;

    public RemoteControl(Command turnOnCommand, Command turnOffCommand) {
        this.turnOnCommand = turnOnCommand;
        this.turnOffCommand = turnOffCommand;
    }

    public void pressTurnOnButton() {
        turnOnCommand.execute();
    }

    public void pressTurnOffButton() {
        turnOffCommand.execute();
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        // Создаем устройство (телевизор)
        TV tv = new TV();

        // Создаем команды для включения и выключения
        Command turnOnCommand = new TurnOnCommand(tv);
        Command turnOffCommand = new TurnOffCommand(tv);

        // Создаем пульт дистанционного управления
        RemoteControl remoteControl = new RemoteControl(turnOnCommand, turnOffCommand);

        // Используем пульт для управления устройством
        remoteControl.pressTurnOnButton();  // Вывод: TV is ON
        remoteControl.pressTurnOffButton(); // Вывод: TV is OFF
    }
}
