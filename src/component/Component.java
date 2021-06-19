package component;

import java.util.ArrayList;

public class Component {
    
    String name;
    boolean installed;
    ArrayList<Component> dependencies = new ArrayList<>();

    public Component(String name) {
        this.name = name;
        installed = false;
    }

    public boolean hasDependency(Component other) {
        return dependencies.contains(other);
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

    public void install() {
        if(!installed) {
            for(Component currentDep: dependencies) {
                currentDep.install();
            }

            System.out.println("Installing " + name);
            installed = true;
        }
    }
}
