package dev.mvc.gamedevice;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.gamedevice.GameDeviceDAO")
public class GameDeviceDAO implements GameDeviceDAOInter {

  
  @Autowired
 private SqlSessionTemplate mybatis;
 
 public GameDeviceDAO(){
 System.out.println("--> GameDeviceDAO created.");
 }
 @Override 
 public int create(GameDeviceVO gamedeviceVO){
 return mybatis.insert("gamedevice.create", gamedeviceVO);
 }
 @Override
 public List<GameDeviceVO> list() {
 return mybatis.selectList("gamedevice.list");
 }
 @Override
 public GameDeviceVO read(int gdno) {
 return mybatis.selectOne("gamedevice.read", gdno);
 }
 @Override
 public int update(GameDeviceVO gamedeviceVO) {
 return mybatis.update("gamedevice.update", gamedeviceVO);
 }
 @Override
 public int delete(int gdno) {
 return mybatis.delete("gamedevice.delete", gdno);
 }
}
