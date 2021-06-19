package controller;

import java.util.ArrayList;

import component.Component;

public class ComponentController {

    private static ComponentController instance = new ComponentController();

    ArrayList<Component> componentsDependencies = new ArrayList<>();
    ArrayList<Component> installedComponents = new ArrayList<>();
    
    public static ComponentController getInstance() {
        return instance;
    }

    public void clear() {
        componentsDependencies.clear();
        installedComponents.clear();
    }

    public void createDepend(String[] cmdLine) {
        if(cmdLine.length < 3)
            return;

        Component newMainComponent = getDepend(cmdLine[1]);

        // It doesn't exist in Dependency list
        if(newMainComponent == null) {
            newMainComponent = new Component(cmdLine[1]);

            // Add Dependencies
            for(int i = 2; i < cmdLine.length; i++) {

                // Check cross dependency
                Component compDep = getDepend(cmdLine[i]);
                if(compDep != null && compDep.hasDependency(newMainComponent) ) {
                    System.out.printf("%s depends on %s, ignoring command\n",
                                        compDep.getName(),
                                        newMainComponent.getName());
                } else {
                    if(compDep == null) {
                        compDep = new Component(cmdLine[i]);
                        addToDependList(compDep);
                    }
                    //Component newCompDep = new Component(cmdLine[i]);
                    newMainComponent.addDependency(compDep);
                    
                }
            }

            addToDependList(newMainComponent);

        } else {
            Component newCompDep = getDepend(cmdLine[2]);
            if(newCompDep != null && newCompDep.hasDependency(newMainComponent)) {
                System.out.printf("%s depends on %s, ignoring command\n", newCompDep.getName(), newMainComponent.getName());
            } else if (newCompDep != null) {
                newMainComponent.addDependency(newCompDep);
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

    public void installComponent(String[] cmdLine) {

        if(cmdLine.length < 2)
            return;

        Component componentToInstall = getDepend(cmdLine[1]);

        if(componentToInstall == null) {
            componentToInstall = new Component(cmdLine[1]);
        }

        componentToInstall.explicitlyInstall();
        addToInstallList(componentToInstall);
    }

    private void addToInstallList(Component installedComponent) {
        if(!installedComponents.contains(installedComponent)) {
            
            for(Component current: installedComponent.getDependencies())
                addToInstallList(current);

            installedComponents.add(installedComponent);
        }
    }

    public void removeComponent(String[] cmdLine) {
        if(cmdLine.length < 2)
            return;

        Component componentToRemove = getDepend(cmdLine[1]);

        if(componentToRemove != null) {
            if(!isUsedByComponent(componentToRemove)) {
                componentToRemove.explicityRemove();
            } else {
                System.out.println(componentToRemove.getName() + " is still needed");
            }
        } else {
            System.out.println(cmdLine[1] + " is not installed");
        }
    }

    private boolean isUsedByComponent(Component comp) {
        for(Component current: componentsDependencies) {
            if(current.hasDependency(comp)) {
                return true;
            }
        }

        return false;
    }

    public void listInstalledComponents() {
        for(Component current: installedComponents) {
            System.out.println(current.getName());
        }
    }
}
