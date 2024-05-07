package repository;

import java.util.*;

import model.Branch;
public class BranchRepository {
	private static final Map<String, Branch> branches = new HashMap<>();

    public static void addBranch(Branch branch) {
        branches.put(branch.getName(), branch);
    }

    public static Branch getBranch(String name) {
        return branches.get(name);
    }
    
    public static List<Branch> getAllBranches() {
        return new ArrayList<>(branches.values());
    }
}
