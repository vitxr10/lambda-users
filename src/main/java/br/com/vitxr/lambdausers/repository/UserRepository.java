package br.com.vitxr.lambdausers.repository;

import br.com.vitxr.lambdausers.dto.User;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper mapper;


    public Long create(User user) {
        mapper.save(user);
        return user.getId();
    }

    public User getById(Long id) {
        return mapper.load(User.class, id);
    }

    public void delete(User user) {
        mapper.delete(user);
    }

    public User update(User user) {
        mapper.save(user, buildExpression(user));
        return user;
    }

    private DynamoDBSaveExpression buildExpression(User user) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("id", new ExpectedAttributeValue(new AttributeValue().withS(String.valueOf(user.getId()))));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
