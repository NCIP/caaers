class BasicCsmPolicy extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("DELETE FROM csm_protection_element");
        execute("DELETE FROM csm_privilege");
        execute("DELETE FROM csm_group");
        execute("DELETE FROM csm_application");

        insert('csm_application',
            [ application_id: -1, application_name: "caaers",
              application_description: "caAERS", active_flag: 0, declarative_flag: 0 ],
            primaryKey: false)
        insert('csm_application',
            [ application_id: -2, application_name: "csm_upt",
              application_description: "UPT", active_flag: 0, declarative_flag: 0 ],
            primaryKey: false)

        insert('csm_group', [ group_id: -1, group_name: 'caaers_admin', application_id: -1 ], primaryKey: false)
        insert('csm_group', [ group_id: -2, group_name: 'caaers_user',  application_id: -1 ], primaryKey: false)

        insert('csm_protection_element',
            [ protection_element_id: -1, protection_element_name: 'caaers',
              protection_element_description: 'caAERS Application',
              object_id: 'caaers', application_id: -1 ], primaryKey: false)

        insert('csm_privilege', [ privilege_id: -1, privilege_name: 'CREATE',
            privilege_description: 'This privilege grants permission to a user to create an entity. This entity can be an object, a database entry, or a resource such as a network connection.' ],
            primaryKey: false)
        insert('csm_privilege', [ privilege_id: -2, privilege_name: 'ACCESS',
            privilege_description: 'This privilege allows a user to access a particular resource.  Examples of resources include a network or database connection, socket, module of the application, or even the application itself.' ],
            primaryKey: false)
        insert('csm_privilege', [ privilege_id: -3, privilege_name: 'READ',
            privilege_description: 'This privilege permits the user to read data from a file, URL, database, an object, etc. This can be used at an entity level signifying that the user is allowed to read data about a particular entry.' ],
            primaryKey: false)
        insert('csm_privilege', [ privilege_id: -4, privilege_name: 'WRITE',
            privilege_description: 'This privilege allows a user to write data to a file, URL, database, an object, etc. This can be used at an entity level signifying that the user is allowed to write data about a particular entity.' ],
            primaryKey: false)
        insert('csm_privilege', [ privilege_id: -5, privilege_name: 'UPDATE',
            privilege_description: 'This privilege grants permission at an entity level and signifies that the user is allowed to update data for a particular entity. Entities may include an object, object attribute, database row etc.' ],
            primaryKey: false)
        insert('csm_privilege', [ privilege_id: -6, privilege_name: 'DELETE',
            privilege_description: 'This privilege permits a user to delete a logical entity. This entity can be an object, a database entry, a resource such as a network connection, etc.' ],
            primaryKey: false)
        insert('csm_privilege', [ privilege_id: -7, privilege_name: 'EXECUTE',
            privilege_description: 'This privilege allows a user to execute a particular resource. The resource can be a method, function, behavior of the application, URL, button etc.' ],
            primaryKey: false)
    }

    void down() {
        execute("DELETE FROM csm_user_group");
        execute("DELETE FROM csm_user_group_role_pg");
        execute("DELETE FROM csm_protection_element");
        execute("DELETE FROM csm_privilege");
        execute("DELETE FROM csm_group");
        execute("DELETE FROM csm_application");
    }
}