package dev.mvc.art;

import java.util.HashMap;
import java.util.List;

import dev.mvc.camera.CameraVO;


public interface ArtDAOInter {

  /**
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="ArtVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(ArtVO vo);
  
  /**
   * 검색된 레코드 수
   * <select id="count" resultType="int" parameterType="HashMap" >
   * @param hashmap 검색 조건
   * @return
   */
  public int count(HashMap hashmap);
  
  /**
   * 검색 목록
   * <select id="list3" resultType="ArtVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<ArtVO> list3(HashMap hashmap);
  
  /**
   * 예술/문화 정보 조회
   * <select id="read" resultType="ArtVO" parameterType="int">
   * @param ano
   * @return
   */
  public ArtVO read(int ano); 
  
  /**
   * 조회수 증가
   * <update id="increaseCnt" parameterType="int">
   * @param ano
   * @return
   */
  public int increaseCnt(int ano);
  
  /**
   * 카메라 글삭제
   * <delete id="delete" parameterType="int">
   * @param ano
   * @return
   */
  public int delete(int ano);
  
  /**
   * 수정
   * @param artVO
   * @return
   */
  public int update(ArtVO artVO);
}
