package qa.service;

import qa.dao.TagDao;
import qa.domain.Tag;

public class TagService {
    private TagDao tagDao;

    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public Tag find(String name) {
        Tag tag = tagDao.find(name);
        if(tag == null) {
            tag = new Tag(name);
        }
        return tag;
    }

    public Tag find(Tag tag) {
        return find(tag.getName());
    }
}
