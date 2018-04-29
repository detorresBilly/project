package project;

import java.util.Map;
import java.util.TreeMap;

public class MachineModel {
	
	public TreeMap<Integer, Instruction> INSTRUCTIONS = new TreeMap<Integer, Instruction>();
	private CPU cpu = new CPU();
	private Memory memory = new Memory();
	private HaltCallback callback;
	private boolean withGUI;
	
	public MachineModel() {
		this(false, null);
	}


	public MachineModel(boolean g, HaltCallback c) {
		withGUI = g;
		callback = c;
		
		//INSTRUCTION_MAP entry for "ADDI"
        INSTRUCTIONS.put(0xC, arg -> {
            cpu.accumulator += arg;
            cpu.incrementIP(1);
        });

        //INSTRUCTION_MAP entry for "ADD"
        INSTRUCTIONS.put(0xD, arg -> {
            int arg1 = memory.getData(cpu.memoryBase+arg);
            cpu.accumulator += arg1;
            cpu.incrementIP(1);
        });

        //INSTRUCTION_MAP entry for "ADDN"
        INSTRUCTIONS.put(0xE, arg -> {
            int arg1 = memory.getData(cpu.memoryBase+arg);
            int arg2 = memory.getData(cpu.memoryBase+arg1);
            cpu.accumulator += arg2;
            cpu.incrementIP(1);
        });
        
      //INSTRUCTION_MAP entry for "SUBI"
        INSTRUCTIONS.put(0xF, arg -> {
            cpu.accumulator -= arg;
            cpu.incrementIP(1);
        });
        
      //INSTRUCTION_MAP entry for "SUB"
        INSTRUCTIONS.put(0x10, arg -> {
            int arg1 = memory.getData(cpu.memoryBase+arg);
            cpu.accumulator -= arg1;
            cpu.incrementIP(1);
        });
        
      //INSTRUCTION_MAP entry for "SUBN"
        INSTRUCTIONS.put(0x11, arg -> {
            int arg1 = memory.getData(cpu.memoryBase+arg);
            int arg2 = memory.getData(cpu.memoryBase+arg1);
            cpu.accumulator += arg2;
            cpu.incrementIP(1);
        });
        
      //INSTRUCTION_MAP entry for "MULI"
        INSTRUCTIONS.put(0x12, arg -> {
            cpu.accumulator = cpu.accumulator * arg;
            cpu.incrementIP(1);
        });
        
      //INSTRUCTION_MAP entry for "MUL"
        INSTRUCTIONS.put(0x13, arg -> {
            int arg1 = memory.getData(cpu.memoryBase+arg);
            cpu.accumulator = cpu.accumulator * arg1;
            cpu.incrementIP(1);
        });
        
      //INSTRUCTION_MAP entry for "MULN"
        INSTRUCTIONS.put(0x14, arg -> {
            int arg1 = memory.getData(cpu.memoryBase+arg);
            int arg2 = memory.getData(cpu.memoryBase+arg1);
            cpu.accumulator = cpu.accumulator * arg2;
            cpu.incrementIP(1);
        });
        
      //INSTRUCTION_MAP entry for "DIVI"
        INSTRUCTIONS.put(0x15, arg -> {
        	if(arg == 0) {
        		throw new DivideByZeroException();
        	}
            cpu.accumulator = cpu.accumulator / arg;
            cpu.incrementIP(1);
        });
        
      //INSTRUCTION_MAP entry for "DIV"
        INSTRUCTIONS.put(0x16, arg -> {
        	if(arg == 0) {
        		throw new DivideByZeroException();
        	}
            int arg1 = memory.getData(cpu.memoryBase+arg);
            cpu.accumulator = cpu.accumulator / arg1;
            cpu.incrementIP(1);
        });
        
      //INSTRUCTION_MAP entry for "DIVN"
        INSTRUCTIONS.put(0x17, arg -> {
        	if(arg == 0) {
        		throw new DivideByZeroException();
        	}
            int arg1 = memory.getData(cpu.memoryBase+arg);
            int arg2 = memory.getData(cpu.memoryBase+arg1);
            cpu.accumulator = cpu.accumulator / arg2;
            cpu.incrementIP(1);
        });
	}


	private class CPU {
		
		private int accumulator;
		private int instructionPointer;
		private int memoryBase;
		
		public void incrementIP(int val) {
			instructionPointer += val;
		}
	}
}
