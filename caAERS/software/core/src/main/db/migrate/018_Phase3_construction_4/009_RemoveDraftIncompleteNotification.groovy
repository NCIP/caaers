class RemoveDraftIncompleteNotification extends edu.northwestern.bioinformatics.bering.Migration {
  void up() {
  	execute("UPDATE task_configuration\n" +
                "   SET message=NULL\n" +
                "       where task_configuration.task_name = 'Submit Report To Physician'");
  }
  void down(){
  	// Not applicable
  }
}