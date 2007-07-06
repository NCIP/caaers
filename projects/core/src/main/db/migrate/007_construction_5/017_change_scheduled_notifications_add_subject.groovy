class ChangeScheduledNotification extends edu.northwestern.bioinformatics.bering.Migration{
	void up() {
       	addColumn('scheduled_notifications','subject', 'string' , nullable:true);    
    }

    void down() {
       dropColumn('scheduled_notifications', 'subject');
    }
}