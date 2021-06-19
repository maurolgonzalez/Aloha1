package component;

import java.util.ArrayList;

public class Component {
    
    String name;
    boolean installed;
    boolean explicityInstalled;
    ArrayList<Component> dependencies = new ArrayList<>();

    public Component(String name) {
        this.name = name;
        installed = false;
        explicityInstalled = false;
    }

    public boolean isExplicityInstalled() {
        return explicityInstalled;
    }

    public void setExplicityInstalled(boolean explicityInstalled) {
        this.explicityInstalled = explicityInstalled;
    }

    public boolean hasDependency(Component other) {
        return dependencies.contains(other);
    }

    public ArrayList<Component> getDependencies() {
        return dependencies;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Component otherComp = (Component) other;

        return this.name.compareTo(otherComp.name) == 0;
    }


    public void addDependency(Component compDep) {
        dependencies.add(0, compDep);
        
    }

    public String getName() {
        return name;
    }

    public void explicitlyInstall() {
        explicityInstalled = true;
        install();
    }

    public void install() {
        if(!installed) {
            for(Component currentDep: dependencies) {
                currentDep.install();
            }

            System.out.println("Installing " + name);
            installed = true;
        }
    }

    public void printInstalled() {
        if(installed) {
            for(Component currentDep: dependencies) {
                currentDep.printInstalled();
            }

            System.out.println(name);
        }
    }

    public void remove() {
        if(installed) {
            for(Component currentDep: dependencies) {
                currentDep.remove();
            }

            System.out.println("Removing " + name);
            installed = false;
        } else {
            System.out.println(name + " is not installed");
        }
    }

    public void explicityRemove() {
        if(!installed) {
            System.out.println(name + " is not installed");
            return;
        }

        remove();
    }
}
