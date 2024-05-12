# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Skill class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Skill instance = new Skill(null);
	}

## 5. Construction (Implementation)

### Class CreateTaskController 

```java
public Optional<Skill> registerSkill(String skillName) {
    Skill newSkill = new Skill(skillName);
    if (!skillRepository.getSkills().contains(newSkill)) {
        try{
            skillRepository.getSkills().add(newSkill);
        } catch (UnsupportedOperationException e){
            System.out.println("Error:" + e.getMessage());
        }
        return Optional.of(newSkill);
    }
    return Optional.empty();
}
```



## 6. Integration and Demo 

* A new option on the Collaborator menu options was added.

* For demo purposes some skills where already added to the system.


## 7. Observations

n/a