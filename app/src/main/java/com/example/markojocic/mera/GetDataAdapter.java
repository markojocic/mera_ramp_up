package com.example.markojocic.mera;

import java.util.ArrayList;

public class GetDataAdapter {

    String repositoryName;
    String loginName;
    Integer repositorySize;
    Boolean hasWiki;


    public String getRepositoryName(){
        return repositoryName;
    }

    public void setRepositoryName(){

        this.repositoryName = repositoryName;
    }



    public  String getLoginName(){

        return loginName;
    }
    public void setLoginName(){

        this.loginName = loginName;
    }



    public Integer getRepositorySize(){

        return repositorySize;
    }
    public void setRepositorySize(){

        this.repositorySize = repositorySize;
    }



    public Boolean getHasWiki(){

        return hasWiki;
    }

    public void setHasWiki(){

        this.hasWiki = hasWiki;
    }
}


