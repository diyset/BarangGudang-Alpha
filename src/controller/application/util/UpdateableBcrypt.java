/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Dian
 */
public class UpdateableBcrypt {

    private final int logRounds = 4;

    public UpdateableBcrypt() {
      
    }
    
    public String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }
    
    public Boolean verifyHash(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }

}
