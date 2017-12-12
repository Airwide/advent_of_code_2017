import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Puzzle_16 {

    public static void main(String[] args){
        if(args.length == 0 || args[0] == null) {
            System.out.println("No args :-(");
            return;
        }
        try {
            Scanner sc = new Scanner(new File(args[0]));
            HashMap<String,Integer> registers = new HashMap<>();
            int allTimeHigh = Integer.MIN_VALUE;

            while(sc.hasNextLine()) {
                Scanner sc2 = new Scanner(sc.nextLine());

                String register = sc2.next();
                if(!registers.containsKey(register)) {
                    registers.put(register,0);
                }
                String operation = sc2.next();
                int operator = Integer.parseInt(sc2.next());
                sc2.next(); //If
                String condReg = sc2.next();
                if(!registers.containsKey(condReg)) {
                    registers.put(condReg,0);
                }
                String cond = sc2.next();
                int condVal = Integer.parseInt(sc2.next());

                if(evalCond(cond, condReg, condVal, registers)) {
                    if(operation.equals("inc")) {
                        registers.put(register, (registers.get(register)+operator));
                    }
                    else {
                        registers.put(register, (registers.get(register)-operator));
                    }
                    if(registers.get(register) > allTimeHigh) {
                        allTimeHigh = registers.get(register);
                    }
                }
            }

            System.out.println(allTimeHigh);
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found! :-(");
        }
    }

    public static boolean evalCond(String condition, String register, int value, HashMap<String,Integer> registers) {
        switch (condition) {
            case "==": return (registers.get(register) == value);
            case "!=": return (registers.get(register) != value);
            case "<":  return (registers.get(register) < value);
            case ">":  return (registers.get(register) > value);
            case "<=": return (registers.get(register) <= value);
            case ">=": return (registers.get(register) >= value);
            default:   return false;
        }
    }
}
