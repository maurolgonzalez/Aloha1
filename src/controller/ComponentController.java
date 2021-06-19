package controller;

import java.util.ArrayList;

import component.Component;

public class ComponentController {

    private static ComponentController instance = new ComponentController();

    ArrayList<Component> componentsDependencies = new ArrayList<>();
    ArrayList<Component> componentsInstalled = new ArrayList<>();
    
    public static ComponentController getInstance() {
        return instance;
    }

    public void createDepend(String[] cmdLine) {
        if(cmdLine.length < 3)
            return;

        Component newMainComponent = getDepend(cmdLine[1]);

        if(newMainComponent == null) {
            
            newMainComponent = new Component(cmdLine[1]);
            // Add Dependencies
            for(int i = 2; i < cmdLine.length; i++) {

                // Check cross dependency
                Component compDep = getDepend(cmdLine[i]);
                if(compDep != null && compDep.hasDependency(newMainComponent) ) {
                    System.out.printf("%s depends on %s, ignoring command\n", compDep.getName(), newMainComponent.getName());
                } else {
                    Component newCompDep = new Component(cmdLine[i]);
                    newMainComponent.addDependency(newCompDep);
                }

                
            }

            addToDependList(newMainComponent);
        } else {
            Component newCompDep = getDepend(cmdLine[2]);
            if(newCompDep != null && newCompDep.hasDependency(newMainComponent)) {
                System.out.printf("%s depends on %s, ignoring command\n", newCompDep.getName(), newMainComponent.getName());
            }
        }
    }


    private void addToDependList(Component comp) {
        componentsDependencies.add(comp);
    }

    private Component getDepend(String componentName) {
        for(Component current: componentsDependencies) {
            if(current.getName().compareTo(componentName) == 0) {
                return current;
            }
        }

        return null;
    }

    public void installDepend(String[] cmdLine) {

        if(cmdLine.length < 2)
            return;

        Component componentToInstall = getDepend(cmdLine[1]);

        if(componentToInstall != null) {
            componentToInstall.install();
        }
    }

    public void removeComponent(String[] cmdLine) {
    }

    public void listComponents(String[] cmdLine) {
    }
}
